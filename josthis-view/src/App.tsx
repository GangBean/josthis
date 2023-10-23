import React, { useState, useEffect } from "react";
import StockList from "./StockList";
import Header from "./Header";
import Footer from "./Footer";
import { StockItemProp } from "./StockItem";

export default function App() {
    const [stocks, setStocks] = useState<StockItemProp[]>([]);

    useEffect(() => {
        // 데이터를 가져와서 상태 업데이트
        fetch("http://localhost:8080/api/stocks")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setStocks(data);
            })
            .catch((error) => {
                console.error("Error 발생", error);
            });
    }, []);

    return (
        <>
            <head>Josthis</head>
            <body>
            <Header />
            <StockList stocks={stocks} />
            <Footer />
            </body>
        </>
    );
}