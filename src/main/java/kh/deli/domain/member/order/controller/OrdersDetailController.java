package kh.deli.domain.member.order.controller;

import com.google.gson.Gson;
import io.lettuce.core.ScriptOutputType;
import kh.deli.domain.member.order.dto.*;
import kh.deli.domain.member.order.service.OrderBasketService;
import kh.deli.domain.member.order.service.OrderOrdersService;
import kh.deli.domain.member.store.dto.BasketDTO;
import kh.deli.domain.member.store.dto.StoreBasketMenuRequestDTO;
import kh.deli.global.entity.MenuDTO;
import kh.deli.global.entity.MenuOptionDTO;
import kh.deli.global.entity.OrdersDTO;
import kh.deli.global.entity.StoreDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/member/order/detail")
public class OrdersDetailController {
    private final OrderOrdersService orderOrdersService;
    private final OrderBasketService orderBasketService;
    private final Gson gson;
    @RequestMapping("")
    public String getStoreInfo(Model model) throws Exception{

       int order_seq=18;

        StoreInfoDTO storeInfoDTO = orderOrdersService.getStoreInfo(order_seq); // 가게정보
        OrdererInfoDTO ordererInfoDTO =orderOrdersService.getOrdererInfo(order_seq); // 주문자정보
        PayInfoDTO payInfoDTO=orderOrdersService.getPayInfo(order_seq); // 결제정보
        OrdersDTO ordersDTO = orderOrdersService.findOrdersBySeq(order_seq); //주문정보

        String menu_list = ordersDTO.getMenu_list();

        BasketDTO basket = gson.fromJson(menu_list, BasketDTO.class);

        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        for (int i = 0; i<basket.getMenuList().size(); i++) {
            MenuDTO menuDTO = orderBasketService.findMenuBySeq(basket.getMenuList().get(i).getMenuSeq());
            List<MenuOptionDTO> menuOptionDTOList = new ArrayList<>();

            for (int k = 0; k < basket.getMenuList().get(i).getOptionSeqList().size(); k++) {
                MenuOptionDTO menuOptionDTO = orderBasketService.findMenuOptionBySeq(basket.getMenuList().get(i).getOptionSeqList().get(k));
                menuOptionDTOList.add(menuOptionDTO);
            }

            int count = basket.getMenuList().get(i).getCount();
            int price = basket.getMenuList().get(i).getPrice();

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(menuDTO, menuOptionDTOList, count, price);

            orderDetailDTOList.add(orderDetailDTO);
        }

        model.addAttribute("orderDetailDTOList", orderDetailDTOList);
        model.addAttribute("storeInfoDTO",storeInfoDTO);
        model.addAttribute("ordererInfoDTO",ordererInfoDTO);
        model.addAttribute("payInfoDTO",payInfoDTO);

        return "/member/order/orderDetail";
    }



}
