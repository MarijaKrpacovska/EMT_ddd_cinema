import axios from '../custom-axios/axios';

const MovieService = {
    fetchMovies: () => {
        return axios.get("/movie");
    },
    fetchMoviesByGenre: () => {
        return axios.get(`/movie/findByGenre/action`);
    },
    fetchMoviesWithPagination: (page,size) => {
        return axios.get("/movie/moviePagination",{params:{
            page, size
            }});
    },
    addMovie: (name, movieLength, genre, publishDate, description,ticketPrice,url,trailerUrl) => {
        return axios.post("/movie/add", {
            "name" : name,
            "movieLength" : movieLength,
            "genre" : genre,
            "publishDate" : publishDate,
            "description" : description,
            "ticketPrice" : ticketPrice,
            "url" : url,
            "trailerUrl" : trailerUrl
        });
    },
    getMovie: (id) => {
        console.log("vleguva vo getMovie")
        return axios.get(`/movie/findById/${id}`);
    },
    rateMovie: (id,rating) => {
        console.log("in rateMovie")
        return axios.post(`/movie/rateMovie/${id}`,null,{params:{
                rating
            }});
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