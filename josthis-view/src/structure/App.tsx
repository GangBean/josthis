import React, { useState, useEffect } from "react";
import Header from "./Header";
import Footer from "./Footer";
import {CardProps} from "../components/cards/Card";
import Main from "./Main";

export default function App() {
    const [cards, setCards] = useState<CardProps[]>([]);

    useEffect(() => {
        // 데이터를 가져와서 상태 업데이트
        fetch("http://localhost:8080/api/stocks")
            .then((response) => response.json())
            .then((card) => {
                console.log(card);
                setCards(card);
            })
            .catch((error) => {
                console.error("Error 발생", error);
            });
    }, []);

    return (
        <>
            <head>
                <title>Josthis</title>
            </head>
            <body>
            <Header />
            <Main cards={cards} />
            <Footer />
            </body>
        </>
    );
}