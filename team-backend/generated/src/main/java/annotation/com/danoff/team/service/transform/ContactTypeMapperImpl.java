package com.danoff.team.service.transform;

import com.danoff.team.dto.ContactTypeDto;
import com.danoff.team.entity.ContactType;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-02-09T18:26:13+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ContactTypeMapperImpl implements ContactTypeMapper {

    @Override
    public ContactType dtoToEntity(ContactTypeDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ContactType contactType = new ContactType();

        contactType.setId( arg0.getId() );
        contactType.setName( arg0.getName() );
        contactType.setPriority( arg0.getPriority() );
        contactType.setValue( arg0.getValue() );

        return contactType;
    }

    @Override
    public ContactTypeDto entityToDto(ContactType arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ContactTypeDto contactTypeDto = new ContactTypeDto();

        contactTypeDto.setId( arg0.getId() );
        contactTypeDto.setName( arg0.getName() );
        contactTypeDto.setPriority( arg0.getPriority() );
        contactTypeDto.setValue( arg0.getValue() );

        return contactTypeDto;
    }
}
