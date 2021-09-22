import axios from '../custom-axios/axios';

const MovieService = {
    fetchMovies: () => {
        return axios.get("/movie");
    }
}

export default MovieService;