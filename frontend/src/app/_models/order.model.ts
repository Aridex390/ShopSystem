import { OrderItem } from "./order-items.model";

export class Order {
    id: string;
    orderDate: Date;
    expiryDate: Date;
    status: string;
    paymentStatus: string;
    orderItems: List<OrderItem>;

    constructor(id: string, orderDate: Date, expiryDate: Date, status: string, paymentStatus: string, orderItems: List<OrderItem>){
        this.id = id;
        this.orderDate = orderDate;
        this.expiryDate = expiryDate;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.orderItems = orderItems
    }
}