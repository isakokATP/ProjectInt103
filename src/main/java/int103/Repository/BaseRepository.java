package int103.Repository;

public class BaseRepository<T, > {
    private String entityName;

    public BaseRepository(String entityName){
        this.entityName = entityName;
    }

}
