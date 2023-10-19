"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importDefault(require("react"));
const react_dom_1 = __importDefault(require("react-dom"));
const App_1 = __importDefault(require("./App")); // App 컴포넌트를 가져옵니다.
react_dom_1.default.render(<react_1.default.StrictMode>
        <App_1.default /> {/* App 컴포넌트를 렌더링합니다. */}
    </react_1.default.StrictMode>, document.getElementById('root') // HTML 파일의 root 엘리먼트에 렌더링합니다.
);
