import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';
import TodoTable from './components/TodoTable';

function App() {
  return (
    <div className="app">
      <Header
        title='Todo App'
        description="Manage your tasks efficiently"
      />
      <TodoFilters/>
      <TodoTable/>
    </div>
  );
}

export default App;