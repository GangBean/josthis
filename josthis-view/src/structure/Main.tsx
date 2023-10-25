import Ranking from "../components/rankings/Ranking";
import CardList from "../components/cards/CardList";
import React from "react";
import {CardProps} from "../components/cards/Card";
import "../style/structure/Main.css";

export interface MainProps {
    cards: CardProps[];
}

export default function Main(props: MainProps) {
    return (
        <main>
            <Ranking url="" name="랭킹 #1" />
            <Ranking url="" name="랭킹 #2" />
            <CardList cards={props.cards} />
        </main>
    );
}