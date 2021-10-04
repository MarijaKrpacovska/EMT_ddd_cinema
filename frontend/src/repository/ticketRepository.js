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
    getTicketReservation: (id) => {return axios.post("/ticket/findReservation/${id}");}
}

export default TicketService;