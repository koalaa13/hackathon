import { makeAutoObservable } from "mobx";
import { Provider } from "mobx-react"
import { Bank } from "../interfaces/Bank";

const bankStore = () => {
    return makeAutoObservable({
        list: [] as Bank[]
    });
};

export default bankStore;