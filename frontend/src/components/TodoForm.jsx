import './TodoForm.css';
import { useState, useEffect } from 'react';

function TodoForm( {isOpen, setIsOpen, categories} ) {

    const [title, setTitle] = useState("");
    const [category, setCategory] = useState("");
    const [newCategory, setNewCategory] = useState("");
    const [status, setStatus] = useState("PENDING");
    const [priority, setPriority] = useState("MEDIUM");
    const [note, setNote] = useState("");

    useEffect(() => {
        if (category !== "__NEWCATEGORY__") {
            setNewCategory("");
        }
    }, [category]);

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
                    {/* TITLE */}
                    <label htmlFor='title'>
                        Title:
                    </label>
                    <input id='title' value={title} onChange={(event) => {setTitle(event.target.value);}}
                    />

                    {/* CATEGORY */}
                    <label htmlFor='category'>
                        Category:
                    </label>
                    <select id='category' value={category} onChange={(event) => {setCategory(event.target.value)}}>
                        <option value="">Select a Category</option>
                        <option value="__NEWCATEGORY__">+ Create New Category...</option>
                        {categories.map((categoryName) => (
                            <option key={categoryName} value={categoryName}> 
                                {categoryName}
                            </option>
                        ))}
                    </select>

                    {category === "__NEWCATEGORY__" &&(
                        <>
                            <label htmlFor='newCategory'>
                                New Category:
                            </label>
                            <input id='newCategory' value={newCategory} onChange={(event) => {setNewCategory(event.target.value)}}>
                            </input>
                        </>
                    )}

                    {/* STATUS */}
                    <label htmlFor='status'>
                        Status:
                    </label>
                    <select id='status' value={status} onChange={(event) => {setStatus(event.target.value)}}>
                        <option value="PENDING">Pending</option>
                        <option value="IN_PROGRESS">In Progress</option>
                        <option value="COMPLETED">Completed</option>
                    </select>

                    {/* PRIORITY */}
                    <label htmlFor='priority'>
                        Priority:
                    </label>
                    <select id='priority' value={priority} onChange={(event) => {setPriority(event.target.value)}}>
                        <option value="MEDIUM">Medium</option>
                        <option value="LOW">Low</option>
                        <option value="HIGH">High</option>
                    </select>

                    {/* NOTE */}
                    <label htmlFor='note'>
                        Note:
                    </label>
                    <textarea id='note' value={note} onChange={(event) => {setNote(event.target.value)}}></textarea>

                </form>
            </div>
        </div>
    );

}

export default TodoForm;