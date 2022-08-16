package ru.gb.Ex.webApp.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    /* Исп. связь @ManyToOne с User (много заказов - один юзер, для истории заказов) */

}
