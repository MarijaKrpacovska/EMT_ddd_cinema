import axios from '../custom-axios/axios_ticket';

const TicketService = {
    makeReservation: (currency,paymentMethod,tickets) => {
        return axios.post("/ticket/makeReservetion", {
            "currency" : currency,
            "paymentMethod" : paymentMethod,
            "tickets" : tickets
        });
    },
    addTicketToReservation: (id, quantity, movie) => {
        return axios.post(`/ticket/addTicketToReservation/${id}`, {
            "qty" : quantity,
            "movie" : movie
        });
    },
    getConfirmedReservations: () => {return axios.get("/ticket/allConfirmedReservations");},

    getTicketReservation: (id) => {return axios.post("/ticket/findReservation/${id}");},
    getActiveReservation: () => {return axios.get("/ticket/findActiveReservation");},
    cancelActiveReservation: () => {return axios.post("/ticket/cancelActiveReservation");},
    confirmActiveReservation: () => {return axios.post("/ticket/confirmActiveReservation");},
    cancelConfirmedReservation: (id) => {return axios.post(`/ticket/cancelConfirmedReservation/${id}`);},
    confirmReservation: (id) => {return axios.post(`/ticket/confirmReservation/${id}`);},
    cancelReservation: (id) => {return axios.post(`/ticket/cancelReservation/${id}`);}

}

export default TicketService;