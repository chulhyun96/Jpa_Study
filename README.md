# Jpa_Study

## 도메인 모델과 테이블 설계
1. 회원, 주문, 상품의 관계
    - 회원은 여러 상품(Item)을 주문(Order)할 수 있다. (일대다)
    - 한 번 주문 시 여러 상품을 선택할 수 있으므로 주문과 상품은 다대다 관계다.
    - 다대다 관계를 이용하지말고 주문상품이라는 또 다른 엔티티클래스를 만들어서 일대다, 다대일 관계로 풀어내서 사용한다.

## 회원 엔티티
이름과 임베디드 타입인 주소 Address 그리고 주문 orders 리스트를 가진다.
임베디드 타입이며 회원과 배송 에닡티에서 사용된다.

## 주문 엔티티
회원이(Member)한번 주문(Order)시 여러 상품(Item)을 주문할 수 있으므로 주문(Order)과 주문상품(OrderItem)은 일대다 관계다.
주문(Order)은 상품(Item)을 주문한 회원(Member)과 배송정보(Delivery), 주문 날짜, 주문 상태(status)를 가지고 있다.
주문 상태는 열거영을 사용하며 주문(ORDER), 취소 (CANCEL)로 표현한다.

## 주문상품 엔티티
주문한 상품 정보와 주문금액, 주문수량 정보를 가지고 있다.

## 상품 엔티티
이름, 가격, 재고수량을 가지고 있으며 상품을 주문하면 재고수량이 줄어든다.
상품의 종류로는 도서, 음반, 영화가 있는데 각각은 사용한 속성이 조금씩 다르다.

## 배송 엔티티
주문시 하나의 배송 정보를 생성하며, 주문과 배송은 일대일 관계다.

## 카테고리 엔티티
상품과 다대다 관계를 맺는데, parent, child로 부모, 자식 카테고리를 연결한다.


# 연관관계 매핑 분석

## 회원과 주문 - member, orders
일대다, 다대일의 양방향 연관관계이다. 따라서 연관관계의 주인은 회원은 여러개의 주문을 할 수 있으므로, 다쪽인 주문이 연관관계의 주인이 된다.
OrderItem.order를 ORDER_ITEM.ORDER_ID 외래키와 매핑한다.

## 주문상품과 주문 - orders_item, orders
다대일 양방향 관계다. OrderItem.item을 ORDER_ITEM.ITEM_ID외래키와 매핑한다

## 주문상품과 상품 orders_item, item
다대일 단방향 관계다. OrderItem.item을 ORDER_ITEM_ITEM_ID 외래 키와 매핑한다.

## 주문과 배송 orders, delivery
일대일 단방향 관계다. Order.delivery를 ORDERS.DELIVERY_ID 외래 키와 매핑한다.

## 카테고리와 상품
@ManyToMany을 사용해서 매핑한다.(실무에서는 @ManyToMany)를 사용하지말고 일대다, 다대일 관계로 풀어서 사용하자.







