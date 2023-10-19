import { useState } from "react";

interface SquareValue {
    value: string,
    onSquareClick: () => void
}

export default function Game() {
    const [history, setHistory] = useState([Array(9).fill(null)]);
    const [currentMove, setCurrentMove] = useState(0);
    const currentSquares = history[currentMove];
    const isXTurn:boolean = currentMove % 2 === 0;

    function handlePlay(nextSquares: any[]) {
        const nextHistory = [...history.slice(0, currentMove + 1), nextSquares];
        setHistory(nextHistory);
        setCurrentMove(nextHistory.length-1);
    }

    function jumpTo(nextMove: number) {
        setCurrentMove(nextMove);
    }

    const moves = history.map((squares, historyIdx) => {
        const description = (historyIdx === 0) ? 'Go to game start.' : 'Go to move #' + historyIdx;
        return (
            <li key={historyIdx}>
                <button onClick={() => jumpTo(historyIdx)}>{description}</button>
            </li>
        )
    });

    return (
        <>
            <div className="game">
                <div className="game-board">
                    <Board isXTurn={isXTurn} squares={currentSquares} onPlay={handlePlay}/>
                </div>
                <div className="game-info">
                    <ol>{moves}</ol>
                </div>
            </div>
        </>
    );
}

interface BoardValue {
    isXTurn: boolean,
    squares: any[],
    onPlay: (nextBoard: any[]) => void
}
function Board({isXTurn, squares, onPlay}: BoardValue) {
    const winner: (number | null) = calculateWinner(squares);
    const status: string = (winner) ? "Winner: " + winner : "Next Player: " + ((isXTurn)?'X':'O');

    function handleClick(i: number) {
        if (squares[i] || winner) return;
        const nextSquare = squares.slice();
        nextSquare[i] = isXTurn ? 'X' : 'O';
        onPlay(nextSquare);
    }

    return (
        <>
            <div className="status">{status}</div>
            <div className="board-row">
                <Square value={squares[0]} onSquareClick={() => handleClick(0)}/>
                <Square value={squares[1]} onSquareClick={() => handleClick(1)}/>
                <Square value={squares[2]} onSquareClick={() => handleClick(2)}/>
            </div>
            <div className="board-row">
                <Square value={squares[3]} onSquareClick={() => handleClick(3)}/>
                <Square value={squares[4]} onSquareClick={() => handleClick(4)}/>
                <Square value={squares[5]} onSquareClick={() => handleClick(5)}/>
            </div>
            <div className="board-row">
                <Square value={squares[6]} onSquareClick={() => handleClick(6)}/>
                <Square value={squares[7]} onSquareClick={() => handleClick(7)}/>
                <Square value={squares[8]} onSquareClick={() => handleClick(8)}/>
            </div>
        </>
    );
}
function Square({value, onSquareClick}: SquareValue) {
    return <button
      className="square"
      onClick={onSquareClick}>{ value }</button>

}
function calculateWinner(squares: number[]) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}
