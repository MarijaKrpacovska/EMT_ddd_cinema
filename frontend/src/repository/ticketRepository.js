import axios from '../custom-axios/axios_ticket';

const TicketService = {
    makeReservation: (currency, tickets) => {
        return axios.post("/ticket/makeReservetion", {
            "currency" : currency,
            "tickets" : tickets
        });
    }
}

export default TicketService;