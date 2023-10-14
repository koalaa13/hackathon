import MapLayer from './components/MapLayer';
import Menu from './components/Menu';

const defaultState = { 
    center: [59.938678, 30.314474],
    zoom: 10
};

function App() {
    return (
        <div className="flex h-screen w-screen">
            <Menu />
            <MapLayer defaultState={defaultState} />
        </div>
    );
}

export default App;
