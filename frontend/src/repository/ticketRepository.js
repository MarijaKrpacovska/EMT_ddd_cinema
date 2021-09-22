import axios from '../custom-axios/axios_ticket';

const TicketService = {
    makeReservation: (currency, tickets) => {
        return axios.post("/ticket/makeReservetion", {
            "currency" : currency,
            "tickets" : tickets
        });
    },
    addTicketToReservation: (id, quantity, movie) => {
        return axios.post("/ticket/addTicketToReservation/${id}", {
            "quantity" : quantity,
            "movie" : movie
        });
    },
    getTicketReservation: (id) => {return axios.post("/ticket/findTicketReservation/${id}");}
}

export default TicketService;