"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importDefault(require("react"));
const client_1 = require("react-dom/client");
require("./styles.css");
const App_1 = __importDefault(require("./App")); // styles.css 파일 가져오기
const container = document.getElementById('root');
const root = (0, client_1.createRoot)(container); // createRoot(container!) if you use TypeScript
root.render(<App_1.default />);
