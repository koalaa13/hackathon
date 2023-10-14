import { YMaps, Map, Placemark, SearchControl, RoutePanel, ZoomControl, GeolocationControl } from '@pbe/react-yandex-maps';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Bank } from '../interfaces/Bank';

interface MapLayerProps {
    defaultState: {
        center: number[];
        zoom: number;
    }
}

const defaultBanks: Bank[] = [
    {
        id: 1,
        longitude: 59.962588,
        latitude: 30.294192,
        tasks: [{ task_id: 1, name: 'task_1' }, { task_id: 2, name: 'task_2' }],
        name: 'Test name 2',
        address: 'Address 1'
    },
    {
        id: 2,
        longitude: 59.946659,
        latitude: 30.356204,
        tasks: [{ task_id: 3, name: 'task_3' }, { task_id: 2, name: 'task_2' }],
        name: 'Test name 2',
        address: 'Address 2'
    }
];


function MapLayer({ defaultState }: MapLayerProps) {
    const [banks, setBanks] = useState([] as Bank[]);
    const [atms, setAtms] = useState([]);

    const SERVER_HOST = 'http://localhost:8080';

    function createBanks(data: any) {
        const result: Bank[] = [];

        for (const bank of data) {
            result.push({
                id: bank.id,
                latitude: bank.latitude,
                longitude: bank.longitude,
                name: bank.bankName,
                address: bank.address,
                tasks: bank.tasks
            });
        }

        return result;
    }

    async function getBanks() {
        const url: string = SERVER_HOST + '/api/v1/banks/all';
        const response = await axios.get(url);
        setBanks((banks) => createBanks(response.data));
    }

    async function getFakeBanks() {
        const response = defaultBanks;
        setBanks((banks) => createBanks(response));
    }

    function placemarkClickHandler(bank: Bank) {
        return async function (e: React.MouseEvent<HTMLElement>) {
            e.preventDefault();

            const url: string = SERVER_HOST + '/api/v1/load';
            // todo:
            // const response = await axios.get(url, {
            //     params: {
            //       id: bank.id
            //     }
            // });
            const fakeResponse = [1, 2, 3];

            setBanks((curBanks) => [
                ...curBanks.filter((curBank) => curBank.id !== bank.id),
                {
                    ...bank,
                    loads: fakeResponse //response
                }
            ]);
        }
    }

    useEffect(() => {
        // todo:
        // getBanks();
        getFakeBanks();
    }, []);

    return (
        <YMaps query={{
            apikey: '1c3c9b4b-82f8-45d1-b365-dc0559fd8f49',
            suggest_apikey: 'b088d678-a72e-4ae5-905d-5fcca43f0a9b'
        }}>
            <Map
                defaultState={defaultState}
                width={'100vw'}
                height={'100vh'}
                modules={["geolocation", "geocode"]}
            >
                <SearchControl options={{ float: "right", provider: 'yandex#search' }} />
                {
                    banks.map((bank: Bank) =>
                        <Placemark
                            key={bank.id}
                            onClick={placemarkClickHandler(bank)}
                            geometry={[bank.longitude, bank.latitude]}
                        />
                    )
                }
                <RoutePanel options={{ float: "right" }} />
                <ZoomControl options={{ position: { right: 0, bottom: 30 } }} />
                <GeolocationControl options={{ float: "right" }} />
            </Map>
        </YMaps>
    );
}

export default MapLayer;