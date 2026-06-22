import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';

function App() {
  return (
    <>
      <Header
        title='Todo App'
        description="Manage your tasks efficiently"
      />
      <TodoFilters
      />
    </>
  );
}

export default App;