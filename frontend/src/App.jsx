import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';
import TodoTable from './components/TodoTable';
import { useState, useEffect } from "react";

function App() {

const [category, setCategory] = useState('');
const [status, setStatus] = useState('');
const [todos, setTodos] = useState([]);

useEffect(() => {
  async function fetchTodos() {
    const response = await fetch("http://localhost:8080/todos");
    const data = await response.json();
    setTodos(data);
    }

    fetchTodos();
}, []);

  return (
    <div className="app">
      <Header
        title='Todo App'
        description="Manage your tasks efficiently"
      />
      <TodoFilters
        category={category}
        setCategory={setCategory}
        status={status}
        setStatus={setStatus}
      />
      <TodoTable
        category={category}
        status={status}
        todos={todos}
      />
    </div>
  );
}

export default App;