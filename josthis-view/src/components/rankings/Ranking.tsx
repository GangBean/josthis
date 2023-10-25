import React from "react";
import "../../style/components/rankings/Ranking.css"

interface RankingProps {
    url: string;
    name: string;
}
export default function Ranking(props:RankingProps) {
    return (
        <div className="rank_section">
            <img src={props.url} alt={props.name}/>
        </div>
    );
}