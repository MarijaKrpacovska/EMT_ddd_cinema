import axios from '../custom-axios/axios';

const MovieService = {
    fetchMovies: () => {
        return axios.get("/movie");
    },
    addMovie: (name, movieLength, genre, publishDate, description,ticketPrice,scheduledMovies) => {
        return axios.post("/movie/add", {
            "name" : name,
            "movieLength" : movieLength,
            "genre" : genre,
            "publishDate" : publishDate,
            "description" : description,
            "ticketPrice" : ticketPrice,
            "scheduledMovies" : scheduledMovies
        });
    },
    getMovie: (id) => {
        console.log("vleguva vo getMovie")
        return axios.get(`/movie/findById/${id}`);
    }

}

export default MovieService;