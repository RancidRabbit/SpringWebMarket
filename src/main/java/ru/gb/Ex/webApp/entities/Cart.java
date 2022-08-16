package ru.gb.Ex.webApp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Cart {

    /* Используем связь @OneToOne с User (один юзер - одна корзина) а также OneToOne с Cart (одна корзина - один заказ) */

}
