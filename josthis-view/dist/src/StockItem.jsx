"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function StockItem(props) {
    return (<main className='stockItem'>
            <img src={props.logoUrl} alt={props.name}/>
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
        </main>);
}
exports.default = StockItem;
