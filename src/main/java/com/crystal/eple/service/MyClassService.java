package com.crystal.eple.service;

import com.crystal.eple.domain.entity.MyClassEntity;
import com.crystal.eple.domain.repository.MyClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MyClassService {
    private final MyClassRepository myClassRepository;

    public MyClassService(MyClassRepository myClassRepository) {
        this.myClassRepository = myClassRepository;
    }

    @Transactional
    public List<MyClassEntity> savemyclass(final MyClassEntity myClassEntity){
        myClassRepository.save(myClassEntity);
        return myClassRepository.findByid(myClassEntity.getId());
    }

    public List<MyClassEntity> retrieveMyclass(final String userId){
        return myClassRepository.findByUser_id(userId);}

    @Transactional
    public List<MyClassEntity> updatemyclass(final MyClassEntity myClassEntity) {

        Optional<MyClassEntity> original = myClassRepository.findById(myClassEntity.getId());

        original.ifPresent(myclass -> {
            myclass.setId(myClassEntity.getId());
            myclass.setTitle(myClassEntity.getTitle());
            myclass.setContent(myClassEntity.getContent());
            myclass.setHomework(myClassEntity.getHomework());
            myclass.setUser((myClassEntity.getUser()));

            myClassRepository.save(myclass);
        });

        return retrieveMyclass(myClassEntity.getUser().getId());
    }

    public List <MyClassEntity> deletemyclass(final MyClassEntity myClassEntity){
        try{
            myClassRepository.delete(myClassEntity);
        }catch (Exception e){

            throw new RuntimeException("error deleting entity" + myClassEntity.getTitle());
        }

        return retrieveMyclass(myClassEntity.getUser().getId());
    }
//    @Transactional
//    public List<MyClassEntity> updatemyclass(Long id, MyClassUpdateRequestDTO requestDTO){
//        MyClassEntity myClassEntity = myClassRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 진도표가 없습니다"));
//        myClassEntity.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getHomework());
//        return id;
//    }

//        MyClassEntity myClassEntity = myClassRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 진도표가 없습니다"));
//        myClassEntity.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getHomework());
//        return id;
//    }
//    @Transactional
//    public MyclassResponseDTO findById(Long id){
//        MyClassEntity myClassEntity = myClassRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException("해당 진도포가 없습니다."));
//        return new MyclassResponseDTO(myClassEntity);
//    }
//    @Transactional(readOnly = true)
//    public List<MyClassListResponseDTO> findallDesc(){
//        return myClassRepository.findAllDesc().stream()
//                .map(MyClassListResponseDTO::new)
//                .collect(Collectors.toList());
//    }
//    @Transactional
//    public void delete(Long id){
//        MyClassEntity myClassEntity = myClassRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException("게시글이 없습니다."));
//        myClassRepository.delete(myClassEntity);
//    }

}
