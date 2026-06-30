import './TodoForm.css';
import { useState } from 'react';

function TodoForm( {isOpen, setIsOpen} ) {

    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("");
    const [status, setStatus] = useState("PENDING");
    const [priority, setPriority] = useState("MEDIUM");
    const [note, setNote] = useState("");

    if(!isOpen) {
        return null;
    }

    return (
        <div className="overlay">
            <div className="modal">
                <header className="modal-header">
                    <h2>New Task</h2>
                </header>

                <form className="modal-body">
                    <label htmlFor='title'>
                        Title:
                    </label>
                    <input 
                        id='title' 
                        value={title} 
                        onChange={(event) => {setTitle(event.target.value);}}
                    />
                </form>
            </div>
        </div>
    );

}

export default TodoForm;