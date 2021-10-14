import axios from '../custom-axios/axios';

const MovieService = {
    fetchMovies: () => {
        return axios.get("/movie");
    },
    fetchMoviesWithPagination: (page,size) => {
        return axios.get("/movie/moviePagination",{params:{
            page, size
            }});
    },
    addMovie: (name, movieLength, genre, publishDate, description,ticketPrice,url,scheduledMovies) => {
        return axios.post("/movie/add", {
            "name" : name,
            "movieLength" : movieLength,
            "genre" : genre,
            "publishDate" : publishDate,
            "description" : description,
            "ticketPrice" : ticketPrice,
            "url" : url,
            "scheduledMovies" : scheduledMovies
        });
    },
    getMovie: (id) => {
        console.log("vleguva vo getMovie")
        return axios.get(`/movie/findById/${id}`);
    },
    scheduleMovie: (id,startTime,endTime) => {
        console.log("vleguva vo schedule")
        return axios.post(`/movie/scheduleMovie/${id}`, {
            "startTime" : startTime,
            "endTime" : endTime
        });
    }

}

export default MovieService;