public class Requesthandler{

    public static List<MediaEntety> sortSearInput(String SearchInput){

        SearchInput = formatSearInput(SearchInput);

        switch(SearchInput){

            case "music":   return MusicDAO.getByName(SearchInput);
            case "film":    return FilmDAO.getByName(SearchInput);
            case "image":   return ImageDAO.getByName(SearchInput);
            default:        return getFromAll(SearchInput);
        }
    }

    private List<MediaEntety> getFromAll(String SearchInput){
        List<MediaEntety> retunList = new List<MediaEntety>();

        returnList.add(MusicDAO.getByName(SearchInput));
        returnList.add(FilmDAO.getByName(SearchInput));
        returnList.add(ImageDAO.getByName(SearchInput));

        return retunList;
    }

    private String formatSearInput(String SearchInput){
        return SearchInput.toLowerCase();
    }
}