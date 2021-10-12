import axios from '../custom-axios/axios_scheduled_movie';

const ScheduledMovieService = {
    fetchScheduledMovies: () => {
        return axios.get("/scheduledMovie");
    },
    fetchScheduledMoviesByMovieId: (id) => {
        return axios.get(`/scheduledMovie/findAllByMovieId/${id}`);
    },
    getScheduledMovie: (id) => {
        console.log("vleguva vo getScheduledMovie")
        return axios.get(`/scheduledMovie/findById/${id}`);
    },
    addScheduledMovie: (sales, startTime, endTime, ticketPrice, movieId) => {
        return axios.post("/scheduledMovie/addScheduledMovie", {
            "sales" : sales,
            "startTime" : startTime,
            "endTime" : endTime,
            "ticketPrice" : ticketPrice,
            "movieId" : movieId
        });
    },
    cancelScheduledMovie: (id) => {
        console.log("vleguva vo cancelScheduledMovie")
        return axios.post(`/scheduledMovie/cancelScheduledMovie/${id}`);
    },
}

export default ScheduledMovieService;