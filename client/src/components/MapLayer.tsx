import { YMaps, Map, Placemark, SearchControl, RoutePanel, ZoomControl } from '@pbe/react-yandex-maps';
import axios from 'axios';
import { useEffect } from 'react';

interface MapLayerProps {
    defaultState: {
        center: number[];
        zoom: number;
    }
}

function MapLayer({defaultState}: MapLayerProps) {
    const SERVER_HOST: string = process.env.REACT_APP_SERVER_HOST ?? 'http://localhost:8080';

    async function getBanks() {
        const url: string = SERVER_HOST + '/banks';
        const response = await axios.get(url);
        console.log(response);
    }
    
    useEffect(() => {
        getBanks();
    }, []);

    return (
        <YMaps query={{ apikey: process.env.REACT_MAP_API_TOKEN, suggest_apikey: process.env.REACT_MAP_SUGGEST_API_TOKEN }}>
            <Map defaultState={defaultState} width={'100vw'} height={'100vh'}>
                <SearchControl options={{ float: "right", provider: 'yandex#search' }} />
                <Placemark geometry={[59.962588,30.294192]} />
                <RoutePanel options={{ float: "right" }} />
                <ZoomControl options={{ position: { right: 0, bottom: 30 } }} />
            </Map>
        </YMaps>
    );
}

export default MapLayer;