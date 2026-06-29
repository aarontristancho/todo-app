import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';
import TodoTable from './components/TodoTable';
import { useState, useEffect } from "react";

function App() {

const [category, setCategory] = useState('');
const [status, setStatus] = useState('');
const [todos, setTodos] = useState([]);
const [loading, setLoading] = useState(true);
const [error, setError] = useState(null);

useEffect(() => {
  async function fetchTodos() {
    try {
      const response = await fetch("http://localhost:8080/todos");
      if (!response.ok) {
        throw new Error(
          `Error ${response.status}: The tasks cannot be loaded.`);
      }
      const data = await response.json();
      setTodos(data);
    }

    catch (error) {
      setError(error.message);
    }
    
    finally {
      setLoading(false);
    }
  }

  fetchTodos();
}, []);

if(loading) {
  return  <p>Loading tasks...</p>;
}
if (error) {
  return <p>{error}</p>;
}

const categories = todos.map(todo => todo.category);

const uniqueCategories = new Set(categories);

const categoriesArray = Array.from(uniqueCategories);


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
        categories={categoriesArray}
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