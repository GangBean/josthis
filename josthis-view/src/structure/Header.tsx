import "../style/structure/Header.css";

export default function Header() {
    return (
        <header className="banner">
            <div className="nav-container">
                <div>
                    <img src="left-image.jpg" alt="로고 이미지"/>
                </div>
                <ul>
                    <li>메뉴 1</li>
                    <li>메뉴 2</li>
                    <li>메뉴 3</li>
                    <li>메뉴 4</li>
                    <li>
                        <svg width="100" height="100" xmlns="http://www.w3.org/2000/svg">
                            <image x="0" y="0" width="50" height="50" xlinkHref="your-image.png" />
                        </svg>
                    </li>
                </ul>
            </div>
        </header>
    );
}