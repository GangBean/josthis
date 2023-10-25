"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const StockItem_1 = __importDefault(require("./StockItem"));
function StockList(props) {
    return (<ul>
            {props.stocks.map((stock) => (<StockItem_1.default id={stock.id} name={stock.name} price={stock.price} logoUrl={stock.logoUrl} code={stock.code} eps={stock.eps} issuedAmount={stock.issuedAmount} marketCap={stock.marketCap} per={stock.per} status={stock.status} key={stock.id}/>))}
        </ul>);
}
exports.default = StockList;
