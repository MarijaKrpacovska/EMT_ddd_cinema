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
    addScheduledMovie: (sales, startDate, startTime, ticketPrice, movieId) => {
        return axios.post("/scheduledMovie/addScheduledMovie", {
            "sales" : sales,
            "startDate" : startDate,
            "startTime" : startTime,
            "ticketPrice" : ticketPrice,
            "movieId" : movieId
        });
    },
    cancelScheduledMovie: (id) => {
        console.log("vleguva vo cancelScheduledMovie")
        return axios.post(`/scheduledMovie/cancelScheduledMovie/${id}`);
    },
    rescheduleMovie: (id,time,date) => {
        console.log("vleguva vo rescheduleMovie")
        return axios.post(`/scheduledMovie/rescheduleMovie/${id}`,null, { params: {
            time, date
        }});
    }
}

export default ScheduledMovieService;