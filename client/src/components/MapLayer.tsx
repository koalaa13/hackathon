import { YMaps, Map, Placemark, SearchControl, RoutePanel, ZoomControl } from '@pbe/react-yandex-maps';

interface MapLayerProps {
    defaultState: {
        center: number[];
        zoom: number;
    }
}

function MapLayer({defaultState}: MapLayerProps) {
    return (
        <YMaps query={{ apikey: '1c3c9b4b-82f8-45d1-b365-dc0559fd8f49', suggest_apikey: 'b088d678-a72e-4ae5-905d-5fcca43f0a9b'}}>
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