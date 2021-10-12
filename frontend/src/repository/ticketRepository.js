import axios from '../custom-axios/axios_ticket';

const TicketService = {
    makeReservation: (reservationTime,currency,reservationStatus,paymentMethod,tickets) => {
        return axios.post("/ticket/makeReservetion", {
            "reservationTime" : reservationTime,
            "currency" : currency,
            "reservationStatus" : reservationStatus,
            "paymentMethod" : paymentMethod,
            "tickets" : tickets
        }).then((data) => console.log(data)). catch((err) => console.log(err));
    },
    addTicketToReservation: (id, quantity, movie) => {
        return axios.post(`/ticket/addTicketToReservation/${id}`, {
            "quantity" : quantity,
            "movie" : movie
        });
    },
    getConfirmedReservations: () => {return axios.get("/ticket/allConfirmedReservations");},

    getTicketReservation: (id) => {return axios.post("/ticket/findReservation/${id}");},
    getActiveReservation: () => {return axios.get("/ticket/findActiveReservation");},
    cancelActiveReservation: () => {return axios.post("/ticket/cancelActiveReservation");},
    confirmActiveReservation: () => {return axios.post("/ticket/confirmActiveReservation");},
    cancelConfirmedReservation: (id) => {return axios.post(`/ticket/cancelConfirmedReservation/${id}`);},

}

export default TicketService;