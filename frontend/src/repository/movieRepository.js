import axios from '../custom-axios/axios';

const MovieService = {
    fetchMovies: () => {
        return axios.get("/movie");
    },
    getMovie: (id) => {
        return axios.get(`/movie/findById/${id}`);
    }

}

export default MovieService;