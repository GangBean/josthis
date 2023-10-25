import "../../style/components/cards/Card.css";

export interface CardProps {
    id: number;
    name: string;
    code: string;
    price: number;
    marketCap: number;
    issuedAmount: number;
    eps: number;
    per: number;
    status: string;
    logoUrl: string;
}

export default function Card(props:CardProps) {
    return (
        <li className="card">
            <div className="img">
                <img src={props.logoUrl} alt={props.name}/>
            </div>
            <div className="price">
                <div>{props.price}</div>
            </div>
            <div className="name">
                <div>{props.name}</div>
                <div>{props.code}</div>
            </div>
            <div className="etc">
                <div>{props.marketCap}</div>
            </div>
        </li>
    );
}