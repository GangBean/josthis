import Card , {CardProps} from "./Card";
import "../../style/components/cards/CardList.css";

interface CardListProps {
    cards: CardProps[]
}

export default function CardList(props: CardListProps) {
    return (
            <div className="cards_section">
                <ol className="cards">
                    {props.cards.map(
                        (card) => (
                            <Card id={card.id} name={card.name} price={card.price} logoUrl={card.logoUrl} code={card.code} eps={card.eps} issuedAmount={card.issuedAmount} marketCap={card.marketCap} per={card.per} status={card.status} key={card.id}/>
                        )
                    )}
                </ol>
            </div>
    );
}