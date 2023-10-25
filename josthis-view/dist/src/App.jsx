"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importStar(require("react"));
const StockList_1 = __importDefault(require("./StockList"));
const Header_1 = __importDefault(require("./Header"));
const Footer_1 = __importDefault(require("./Footer"));
function App() {
    const [stocks, setStocks] = (0, react_1.useState)([]);
    (0, react_1.useEffect)(() => {
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
    return (<>
            <head>Josthis</head>
            <body>
            <Header_1.default />
            <StockList_1.default stocks={stocks}/>
            <Footer_1.default />
            </body>
        </>);
}
exports.default = App;
