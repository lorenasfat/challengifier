﻿using Business.DTOs;
using Business.Mappers;
using Business.Services.Interfaces;
using DataAccess.Entities;
using DataAccess.UnitOfWork;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Business.Services
{
    public class ChallengeService : IChallengeService
    {
        private readonly IUnitOfWork _unitOfWork;
        public ChallengeService(IUnitOfWork unitOfWork)
        {
            _unitOfWork = unitOfWork;
        }
        public void AddChallenge(ChallengeDto challenge)
        {
            try
            {
                _unitOfWork.ChallengeRepository.Create(challenge.ToDbEntity());
                _unitOfWork.ChallengeRepository.Save();
                _unitOfWork.Commit();
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void DeleteChallenge(Guid challengeId)
        {
            try
            {
                var dbChalenge = _unitOfWork.ChallengeRepository.GetById(challengeId);
                if (dbChalenge != null)
                {
                    _unitOfWork.ChallengeRepository.Delete(dbChalenge);
                }
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        public void Dispose()
        {
            _unitOfWork.Dispose();
        }

        public IEnumerable<ChallengeDto> GetAllChallenges()
        {
            var challenges = _unitOfWork.ChallengeRepository.All();
            return challenges.ToDtos();
        }

        public ChallengeDto GetChallengeById(Guid challengeId)
        {
            return _unitOfWork.ChallengeRepository.GetById(challengeId).ToDto();
        }

        public ChallengeDto GetChallengeByName(string name)
        {
            return _unitOfWork.ChallengeRepository.All().FirstOrDefault(c => c.Name.ToUpper().Contains(name.ToUpper())).ToDto();
        }

        public void UpdateChallenge(ChallengeDto challenge)
        {
            try
            {
                var dbChallenge = _unitOfWork.ChallengeRepository.GetById(challenge.Id);
                if (dbChallenge == null)
                {
                    AddChallenge(challenge);
                }
                else
                {
                    SetUpChallenge(challenge, dbChallenge);
                    _unitOfWork.ChallengeRepository.Save();
                    _unitOfWork.Commit();
                }
            }
            catch (Exception)
            {
                _unitOfWork.RollBack();
                throw;
            }
        }

        private void SetUpChallenge(ChallengeDto challenge, Challenge dbChallenge)
        {
            dbChallenge.Description = challenge.Description;
            dbChallenge.Name = challenge.Name;
            dbChallenge.Suggested_Time_UnitsId = challenge.Suggested_Time_UnitsId;
            dbChallenge.Suggested_Time_Number = challenge.Suggested_Time_Number;
        }
    }
}