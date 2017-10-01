package io.stack.pj.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@NoRepositoryBean
public interface DefaultJpaRepository<R> extends JpaRepository<R, Long>, JpaSpecificationExecutor<R>{

}
