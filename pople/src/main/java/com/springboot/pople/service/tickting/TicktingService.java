package com.springboot.pople.service.tickting;

import com.springboot.pople.dto.MovieDTO;
import com.springboot.pople.dto.OrderHistDTO;
import com.springboot.pople.dto.OrderMovieDTO;
import com.springboot.pople.dto.TicktingDTO;
import com.springboot.pople.entity.*;
import com.springboot.pople.repository.MovieImgRepository;
import com.springboot.pople.repository.OrderRepository;
import com.springboot.pople.repository.TicktingRepository;
import com.springboot.pople.repository.UsersRepository;
import com.springboot.pople.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TicktingService {

    private final MovieRepository movieRepository;
    private final UsersRepository usersRepository;
    private final MovieImgRepository movieImgRepository;
    private final TicktingRepository ticktingRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public Long register(TicktingDTO ticktingDTO) {
        Tickting tickting = modelMapper.map(ticktingDTO, Tickting.class);
        Long ticktingid = ticktingRepository.save(tickting).getId();


        return ticktingid;
    }



    // 상품 주문 서비스
    public Long order(TicktingDTO ticktingDTO, String email){

     Movie movie1 =  movieRepository.findByMovieName(ticktingDTO.getMovieName());
        ticktingDTO.setMovieCode(movie1.getMovieid());


        // 주문 상품에 대한 기본 정보 조회
        Movie movie = movieRepository.findById(ticktingDTO.getMovieCode())
                .orElseThrow(EntityNotFoundException::new);

        // 현재 로그인한 회원의 이메일(아이디) 정보를 이용해서 회원 정보조회
        Users users = usersRepository.findByEmail(email);

        // 주문 상품목록을 저장할 객체 생성
        List<OrderMovie> orderMovieList = new ArrayList<>();

        // 주문할 상품 엔티티와 주문 수량을 이용하여 주문 상품 엔티티를 생성
        OrderMovie orderMovie = OrderMovie.createOrderItem(movie, ticktingDTO.getCount());

        // 생성된 주문 상품 엔티티를 리스트에 보관
        orderMovieList.add(orderMovie);
        // 회원정보, 주문할 상품 리스트 정보를 가지고 주문 엔티티 생성
        Order order = Order.createOrder(users, orderMovieList);
        // 생성된 주문 엔티티를 저장
        orderRepository.save(order);

        return order.getId();

    }
    // 주문 목록 서비스
    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable){

        // 1. 사용자의 아이디(email)와 페이징 조건을 이용해 주문 목록 조회 요청
        List<Order> orders = orderRepository.findOrders(email, pageable);

        // 2. 사용자의 총 주문 개수
        Long totalCount = orderRepository.countOreder(email);

        // 3. 검색하여 가져온 주문 목록을 순회하여 구매이력 페이지에 전달할 DTO생성
        List<OrderHistDTO> orderHistDTOS = new ArrayList<>();

        for(Order order : orders){
            OrderHistDTO orderHistDTO = new OrderHistDTO(order);// 주문내역

            // 성능저하 : for문 순회할 때마다 매번 조회쿼리문이 추가 되어 실행
            /*
            쿼리문 실행결과 =>
                from order_item orderitems0_  where   orderitems0_.order_id=?

                해결 방안
                # 기본 batch size 설정
                # 조회 쿼리 한 번으로 지정한 사이즈 만큼 한 번에 조회
                spring.jpa.properties.hibernate.default_batch_fetch_size=1000

            쿼리문 실행 결과 =>
                from order_item orderitems0_  where  orderitems0_.order_id in ( ?, ?, ?, ? )
             */
            List<OrderMovie> orderItems = order.getOrderItems();// 주문상품


          for(OrderMovie orderItem: orderItems){     // 주문 상품 대표 이미지 조회: 주문서에 있는 상품entity로 통행 상품id추출하여 상품대표상품이미지 조회
                MovieImg itemImg = movieImgRepository
                        .findByMovie_MovieidAndRepImgYn((orderItem.getMovie().getMovieid()),"Y");

                // 주문 상품 정보(수량, 개수...), 상품 이미지
                OrderMovieDTO orderMovieDTO = new OrderMovieDTO(orderItem, itemImg.getImgUrl());

                // 주문이력 리스트에 주문상품 추가
                orderHistDTO.addOrderitemDTO(orderMovieDTO);

                log.info("==> 주문 이력 상품 목록");
                log.info(orderItem.getMovie().toString());
               log.info(itemImg.getImgUrl().toString());
                log.info(orderMovieDTO.toString());
                log.info("===");

            }// end for (orderItems : 주문서에 있는 주문상품목록)

            orderHistDTOS.add(orderHistDTO); // 주문이력 List객체에 주문이력 상품 추가

            log.info("==> 고객이 주문한 주문서목록 (1:N)");
            log.info(orderHistDTOS.toString());
            log.info("===");

        } // end for (orders: 주문목록)


        return new PageImpl<>(orderHistDTOS, pageable, totalCount);
    }



    // 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지 검사, 같은 경우 true반환
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Users curMember = usersRepository.findByEmail(email);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        // 현재 로그인 사용자와 상품주문한 사용자가 동일한지 검사
        Users saveMember = order.getUsers();
        if (!StringUtils.equals(curMember.getEmail(), saveMember.getEmail())){
            return false;// 일치하지 않으면 false
        }

        return true;
    }

    // 주문 취소 상태로 변경하면 변경 감지 기능에 의해서 트랜잭션이 끝날 대 update쿼리가 실행
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

//        order.cancelOrder();
    }





    // 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지 검사, 같은 경우 true반환
//    @Transactional(readOnly = true)
//    public boolean validateOrder(Long orderId, String email){
//        Users curMember = usersRepository.findByEmail(email);
//
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        // 현재 로그인 사용자와 상품주문한 사용자가 동일한지 검사
//        Users saveMember = order.getUsers();
//        if (!StringUtils.equals(curMember.getEmail(), saveMember.getEmail())){
//            return false;// 일치하지 않으면 false
//        }
//
//        return true;
//    }





}
