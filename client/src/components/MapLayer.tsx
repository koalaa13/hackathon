import { YMaps, Map, Placemark, SearchControl, RoutePanel, ZoomControl, GeolocationControl } from '@pbe/react-yandex-maps';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Bank } from '../interfaces/Bank';
import { useStore } from '../stores/store';

interface MapLayerProps {
    defaultState: {
        center: number[];
        zoom: number;
    }
}

function MapLayer({ defaultState }: MapLayerProps) {
    const [banks, setBanks] = useState([] as Bank[]);
    const { bankStore } = useStore();

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
                tasks: bank.tasks,
                isATM: bank.isATM
            });
        }

        return result;
    }

    async function getBanks() {
        const url: string = SERVER_HOST + '/api/v1/banks/all';
        const response = await axios.get(url);
        setBanks(() => createBanks(response.data));
    }

    function bankClickHandler(bank: Bank) {
        return async function (e: React.MouseEvent<HTMLElement>) {
            e.preventDefault();

            const url: string = SERVER_HOST + '/api/v1/load';
            const response = await axios.get(url + `/${bank.id}`);
            
            setBanks((curBanks) => [
                ...curBanks.filter((curBank) => curBank.id !== bank.id),
                {
                    ...bank,
                    loads: response.data
                }
            ]);

            bankStore[0] =  {
                ...bank,
                load: response.data
            };
        }
    }

    useEffect(() => {
        getBanks();
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
                            onClick={bankClickHandler(bank)}
                            geometry={[bank.latitude, bank.longitude]}
                            options={{ iconColor: bank.isATM ? 'red' : 'blue' }}
                        />
                    )
                }
                <ZoomControl options={{ position: { right: 0, bottom: 30 } }} />
                <GeolocationControl options={{ float: "right" }} />
            </Map>
        </YMaps>
    );
}

export default MapLayer;