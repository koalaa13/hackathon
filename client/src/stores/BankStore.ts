import { makeAutoObservable } from "mobx";
import { Bank } from "../interfaces/Bank";

const bankStore = () => {
    return makeAutoObservable<Bank[]>([] as Bank[]);
};

export default bankStore;