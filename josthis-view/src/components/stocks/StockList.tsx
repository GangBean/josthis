import StockItem, {StockItemProp} from "./StockItem";

interface StockListProp {
    cards: StockItemProp[]
}

export default function StockList(props: StockListProp) {
    return (
        <ul>
            {props.cards.map(
                (stock) => (
                    <StockItem id={stock.id} name={stock.name} price={stock.price} logoUrl={stock.logoUrl} code={stock.code} eps={stock.eps} issuedAmount={stock.issuedAmount} marketCap={stock.marketCap} per={stock.per} status={stock.status} key={stock.id}/>
                )
            )}
        </ul>
    );
}