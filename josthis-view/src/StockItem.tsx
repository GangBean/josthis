export interface StockItemProp {
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

export default function StockItem(props: StockItemProp) {
    return (
        <main className='stockItem'>
            <img src={props.logoUrl} alt={props.name} />
            <div className='info'>
                <div hidden className='id'>{props.id}</div>
                <div className='name'>{props.name}</div>
                <div className='code'>{props.code}</div>
                <div className='price'>{props.price.toLocaleString()}</div>
                <div className='marketCap'>{props.marketCap.toLocaleString()}</div>
                <div className='issuedAmount'>{props.issuedAmount.toLocaleString()}</div>
                <div className='eps'>{props.eps.toLocaleString()}</div>
                <div className='per'>{props.per}</div>
                <div className='status'>{props.status}</div>
            </div>
        </main>
    );
}
