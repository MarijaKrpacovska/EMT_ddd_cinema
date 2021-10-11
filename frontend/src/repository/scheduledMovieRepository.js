import axios from '../custom-axios/axios_scheduled_movie';

const ScheduledMovieService = {
    fetchScheduledMovies: () => {
        return axios.get("/scheduledMovie");
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
}

export default ScheduledMovieService;