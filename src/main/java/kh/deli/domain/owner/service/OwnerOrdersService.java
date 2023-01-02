package kh.deli.domain.owner.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kh.deli.domain.owner.dto.OwnerOrderMngRequestDTO;
import kh.deli.domain.owner.mapper.OwnerOrdersMapper;
import kh.deli.global.entity.OrdersDTO;
import kh.deli.global.util.alarm.AlarmEndpoint;
import kh.deli.global.util.alarm.NoticeRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerOrdersService {

    private final OwnerOrdersMapper ordersMapper;
    private final AlarmEndpoint alarmEndpoint;
    private final HttpSession session;

    private final Gson gson;

    public OrdersDTO findBySeq(int seq) {
        Optional<OrdersDTO> order = Optional.ofNullable(ordersMapper.findBySeq(seq));
        return order.orElse(new OrdersDTO());
    }

    public List<OrdersDTO> getListByStoreSeq(Integer ownerAccSeq) {
        Optional<List<OrdersDTO>> orders = Optional.ofNullable(ordersMapper.getListByStoreSeq(ownerAccSeq));
        return orders.orElse(new ArrayList<>());
    }

    public List<OwnerOrderMngRequestDTO> getOrderMngList(int storeSeq) {
        Optional<List<OwnerOrderMngRequestDTO>> orderMngList
                = Optional.ofNullable(ordersMapper.getOrderMngList(storeSeq));
        return orderMngList.orElse(new ArrayList<>());
    }

    @Transactional
    public void updateStatus(Map<String, String> changeInfo) {
        String checkedSeqListJson = changeInfo.get("checkedSeqListJson");

        List<Integer> accSeqList = gson.fromJson(
                checkedSeqListJson,
                new TypeToken<List<Integer>>() {
                }.getType());

        checkedSeqListJson = checkedSeqListJson.replace("[", "(");
        checkedSeqListJson = checkedSeqListJson.replace("]", ")");

        String newStatus = changeInfo.get("newStatus");


        if (checkedSeqListJson != "[]") {
            // 상태 업데이트
            ordersMapper.updateStatus(checkedSeqListJson, newStatus);

            // 상태 업데이트에 대한 유저에게 알림

            for (Integer accSeq : accSeqList) {
                NoticeRequestDTO noticeRequestDTO = NoticeRequestDTO.builder()
                        .to_acc_seq(accSeq)
                        .title("주문하신 상품상태 변경: " + newStatus)
                        .content("")
                        .build();
                alarmEndpoint.OnMessage(noticeRequestDTO, session);
            }
        }


    }
}
