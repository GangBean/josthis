import React from 'react';
import { createRoot } from 'react-dom/client';
import './styles.css';
import App from "./App"; // styles.css 파일 가져오기

const container = document.getElementById('root');
const root = createRoot(container!); // createRoot(container!) if you use TypeScript
root.render(<App/>);